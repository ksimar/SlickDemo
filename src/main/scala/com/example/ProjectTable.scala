package com.example

//import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global

trait ProjectTable extends EmployeeTable{

  private[example] class ProjectTable(tag: Tag) extends Table[Project](tag, "project_table") {
    val id = column[Int]("id",O.PrimaryKey)
    val name = column[String]("name")

    def employeeProjectFK = foreignKey("employee_project_fk", id, employeeTableQuery)(_.id)
    def * = (id, name) <>(Project.tupled,Project.unapply)

  }
  val projectTableQuery = TableQuery[ProjectTable]

}

trait ProjectComponent extends ProjectTable {
  this: DBComponent =>
  //val db = Database.forConfig("myPostgresDB")
  def create = db.run(projectTableQuery.schema.create)

  def insert(proj: Project) = db.run{
    projectTableQuery += proj
  }

  def deleteById(projId: Int) = {
    db.run(projectTableQuery.filter(_.id === projId).delete)
  }

  def updateNameById(projID: Int, name: String) = {

    db.run(projectTableQuery.filter(_.id===projID).map(record=>(record.name)).update(name))
  }

  def upsert(proj: Project): Future[Int] = {
    db.run(projectTableQuery.insertOrUpdate(proj))
  }

  def getAll() = {
    db.run(projectTableQuery.to[List].result)
  }

  def search(id: Int): Future[Option[Project]] = {
    db.run(projectTableQuery.filter(_.id===id).result.headOption)
  }

  def combinedActions(proj1: Project, proj2: Project) = {
    val create = projectTableQuery.schema.create
    val insert1 = projectTableQuery += proj1
    val insert2 = projectTableQuery += proj2
    val insert3 = projectTableQuery += Project(11, "abc")
    val select = projectTableQuery.to[List].result
    val select2 = projectTableQuery.filter(_.id === 1).to[List].result
    val seq = insert1.andThen(insert3).andThen(insert2).cleanUp {
      case Some(_) => select
      case _ => select2
    }

    db.run(seq)
  }

  def joinEmployee() = {
    val join = for{
      (p,e) <- projectTableQuery join employeeTableQuery
    } yield((p,e))
    db.run(join.to[List].result)
  }

//  def leftJoinEmployee() = {
//    val join = for{
//      (p,e) <- employeeTableQuery joinLeft projectTableQuery on(_.id == _.id)
//    } yield((p,e))
//    db.run(join.to[List].result)
//  }
//
//  def fullJoinEmployee() = {
//    val join = for{
//      (p,e) <- projectTableQuery joinFull employeeTableQuery on(_.id == _.id)
//    } yield(p,e)
//    db.run(join.to[List].result)
//  }

}

//object ProjectComponent extends ProjectComponent with PostgresComponent
object ProjectComponent extends ProjectComponent with MySqlComponent