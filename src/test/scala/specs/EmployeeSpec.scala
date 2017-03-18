package specs

import com.example.{Employee, EmployeeComponent}
import dBConnection.DBCompForTesting
import org.scalatest.AsyncFunSuite

class EmployeeSpec extends AsyncFunSuite with EmployeeComponent with DBCompForTesting {


  test("It should insert a row") {
    val emp = Employee(4, "Jatin", 20)
    insert(emp).map(x => assert(x == 1))
  }

  test("It should delete a row") {
    deleteById(2).map(x => assert(x == 1))
  }

  test("It should update name ") {
    updateNameById(2, "Suraj").map(x => assert(x == 1))
  }

  test("It should insert or update an employee") {
    upsert(Employee(3, "sonam", 10)).map(x => assert(x == 1))
  }

  test("It should retrieve all records") {
    getAll().map(list => assert(list.size == 2))
  }

  test("It should print max experience of employee") {
    maxExperience.map(x => assert(x == Some(20.0)))
  }

  test("It should return a record") {
    plainSQL.map(vector => assert(vector == Vector((1,"Simar"))) )
  }


}
