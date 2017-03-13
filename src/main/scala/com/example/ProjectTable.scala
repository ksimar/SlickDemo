package com.example

import slick.jdbc.PostgresProfile.api._
//import slick.jdbc.MySQLProfile.api._

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

}

object ProjectComponent extends ProjectComponent with PostgresComponent
//object ProjectComponent extends ProjectComponent with MySqlComponent