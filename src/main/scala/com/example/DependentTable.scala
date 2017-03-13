package com.example

import slick.jdbc.PostgresProfile.api._
//import slick.jdbc.MySQLProfile.api._

trait DependentTable extends EmployeeTable{

  private[example] class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependent_table") {
     val id = column[Int]("id", O.Unique)
    val name = column[String]("name")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age")

    //def pk = primaryKey("dependent_pk", (id,name))
    def employeeDependentFK = foreignKey("employee_dependent_fk", id, employeeTableQuery)(_.id)
    def * = (id, name, relation, age) <>(Dependent.tupled, Dependent.unapply)
  }

  val dependentTableQuery = TableQuery[DependentTable]

}

trait DependentComponent extends DependentTable {
   this: DBComponent =>
  import driver.api._
  def create = db.run(dependentTableQuery.schema.create)
  def insert(dependent: Dependent) = db.run( dependentTableQuery += dependent )
}

//object DependentComponent extends DependentComponent with MySqlComponent
object DependentComponent extends DependentComponent with PostgresComponent
