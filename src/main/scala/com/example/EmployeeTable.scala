package com.example

//import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._


trait EmployeeTable {

  private[example] class EmployeeTable(tag: Tag) extends Table[Employee](tag, "experience") {
    val id = column[Int]("id", O.PrimaryKey)
    val name = column[String]("name")
    val experience = column[Double]("experience")

    def * = (id, name, experience) <>(Employee.tupled, Employee.unapply)

  }

  val employeeTableQuery = TableQuery[EmployeeTable]

}

trait EmployeeComponent extends EmployeeTable {
  this: DBComponent =>

   import driver.api._
  //val db = Database.forConfig("myPostgresDB")
  def create = db.run(employeeTableQuery.schema.create)
  def insert(emp: Employee) = db.run{
    employeeTableQuery += emp
  }
  def deleteById(empId: Int) = {
    db.run(employeeTableQuery.filter(_.id === empId).delete)
  }

  def updateNameById(empID: Int, name: String) = {

    db.run(employeeTableQuery.filter(_.id===empID).map(record=>(record.name)).update(name))
  }

  def upsert(emp: Employee): Future[Int] = {
    db.run(employeeTableQuery.insertOrUpdate(emp))
  }

  def getAll() = {
    db.run(employeeTableQuery.result)
  }

  def search(id: Int) = {
    db.run(employeeTableQuery.filter(_.id===id).result)
  }


}

//object EmployeeComponent extends EmployeeComponent with PostgresComponent
object EmployeeComponent extends EmployeeComponent with MySqlComponent
