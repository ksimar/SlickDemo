package com.example

//import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._

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

  def insert(prod: Project) = db.run{
    projectTableQuery += prod
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
    db.run(projectTableQuery.result)
  }

  def search(id: Int) = {
    db.run(projectTableQuery.filter(_.id===id).result)
  }

}

//object ProjectComponent extends ProjectComponent with PostgresComponent
object ProjectComponent extends ProjectComponent with MySqlComponent