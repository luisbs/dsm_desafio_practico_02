import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.udb.dsm.desafio_practico_02.R

class StudentListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Example data
        val students = listOf(
            Student("John", "Doe", "10th", "Math", 85.5),
            Student("Jane", "Smith", "11th", "Science", 92.0)
        )

        val adapter = StudentAdapter(students)
        recyclerView.adapter = adapter
    }
}
