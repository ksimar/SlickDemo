package com.example

import scala.concurrent.ExecutionContext.Implicits.global

object Hello extends App {

  val emp = Employee(1, "simar", 20)
  EmployeeComponent.create
  val insertRes = EmployeeComponent.insert(emp)
  val res = insertRes.map{res => s"$res inserted"}
  res.map(println(_))
  ProjectComponent.create
  DependentComponent.create
  val insert2 = EmployeeComponent.insert(Employee(2, "charmy", 10))
  val insert3 = EmployeeComponent.insert(Employee(3, "aman", 7))
  val insert4 = EmployeeComponent.insert(Employee(4, "ruchi", 2))
  val insert5 = EmployeeComponent.insert(Employee(5, "sonam", 0))
  val insertProject1 = ProjectComponent.insert(Project(1, "Carbon Data"))
  val insertProject2 = ProjectComponent.insert(Project(2, "Fitfyles"))
  val insertDependent1 = DependentComponent.insert(Dependent(1, "abc", "xyz", Some(12)))
  val insertDependent2 = DependentComponent.insert(Dependent(2, "def", "xyz", None))
  val deleteEmp1 = EmployeeComponent.deleteById(5)
  val updateEmp1 = EmployeeComponent.updateNameById(4,"Manan")
  println("deleted")

  Thread.sleep(20000)

  //val res = insertRes.map(res => )
}
