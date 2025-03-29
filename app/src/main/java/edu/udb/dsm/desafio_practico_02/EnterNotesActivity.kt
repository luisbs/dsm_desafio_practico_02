import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.udb.dsm.desafio_practico_02.R

class EnterGradesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_notes)

        val editTextName: EditText = findViewById(R.id.editTextName)
        val editTextLastName: EditText = findViewById(R.id.editTextLastName)
        val spinnerGrade: Spinner = findViewById(R.id.spinnerGrade)
        val spinnerSubject: Spinner = findViewById(R.id.spinnerSubject)
        val editTextFinalGrade: EditText = findViewById(R.id.editTextFinalGrade)
        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)

        // Example data for grades and subjects
        val grades = arrayOf("10th", "11th", "12th")
        val subjects = arrayOf("Math", "Science", "History")

        val gradeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, grades)
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGrade.adapter = gradeAdapter

        val subjectAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, subjects)
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSubject.adapter = subjectAdapter

        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString()
            val lastName = editTextLastName.text.toString()
            val grade = spinnerGrade.selectedItem.toString()
            val subject = spinnerSubject.selectedItem.toString()
            val finalGrade = editTextFinalGrade.text.toString().toDoubleOrNull()

            if (finalGrade != null) {
                // Handle the submission, e.g., save to database or API
                Toast.makeText(this, "Grade submitted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a valid final grade", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
