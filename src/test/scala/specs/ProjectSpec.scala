package specs

import com.example.{Project, ProjectComponent}
import dBConnection.DBCompForTesting
import org.scalatest.AsyncFunSuite

/**
  * Created by simar on 16/3/17.
  */
class ProjectSpec extends AsyncFunSuite with ProjectComponent with DBCompForTesting{


  test("It should insert a row") {
    val proj = Project(2, "Play")
    insert(proj).map(x => assert(x == 1))
  }

  test("It should delete a row") {
    deleteById(1).map(x => assert(x == 1))
  }

  test("It should update name ") {
    updateNameById(1, "Java").map(x => assert(x == 1))
  }

  test("It should insert or update an project") {
    upsert(Project(2, "Spark")).map(x => assert(x == 1))
  }

  test("It should retrieve all records") {
    getAll().map(list => assert(list.size == 1))
  }

  test("It should search a record") {
    search(2).map(record => assert(record.size == 1))
  }

//  test("It should left join employees with project") {
//     // leftJoinEmployee()
//  }

}
