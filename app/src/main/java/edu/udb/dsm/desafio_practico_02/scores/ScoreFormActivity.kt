package edu.udb.dsm.desafio_practico_02.scores

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import edu.udb.dsm.desafio_practico_02.AppBaseActivity
import edu.udb.dsm.desafio_practico_02.R
import kotlin.reflect.KClass

class ScoreFormActivity : AppBaseActivity(), AdapterView.OnItemSelectedListener {
    override var parentActivity: KClass<*>? = ListScoresActivity::class
    override val activityLayout = R.layout.activity_score_form
    override val activityTitle = R.string.score_store

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nameInput: EditText = findViewById(R.id.score_name)
        val lastnameInput: EditText = findViewById(R.id.score_lastname)
        val scoreInput: EditText = findViewById(R.id.score_score)

        val gradeInput: Spinner = findViewById(R.id.score_grade)
        val gradeAdapter = arrayAdapter(R.array.score_grades)
        gradeInput.adapter = gradeAdapter
        gradeInput.onItemSelectedListener = this

        val subjectInput: Spinner = findViewById(R.id.score_subject)
        val subjectAdapter = arrayAdapter(R.array.score_subjects)
        subjectInput.adapter = subjectAdapter
        subjectInput.onItemSelectedListener = this

        // load editing
        val uid = intent.getStringExtra("entry")
        if (uid !== null) {
            Score.get(uid) //
                .addOnFailureListener(::failureListener)
                .addOnSuccessListener {
                    val score = it.getValue(Score::class.java)
                    nameInput.setText(score?.name)
                    lastnameInput.setText(score?.lastName)
                    scoreInput.setText(score?.score.toString())
                    gradeInput.setSelection(gradeAdapter.getPosition(score?.grade))
                    subjectInput.setSelection(subjectAdapter.getPosition(score?.subject))
                    // TODO guardarlo correctamente
                }
        }

        clickListener(R.id.score_store) {
            val nameValue = nameInput.text.toString().trim()
            val lastnameValue = lastnameInput.text.toString().trim()
            val error = when {
                nameValue.isEmpty() -> R.string.score_name_err
                lastnameValue.isEmpty() -> R.string.score_lastname_err
                gradeValue === null || gradeValue!!.isEmpty() -> R.string.score_grade_err
                subjectValue === null || subjectValue!!.isEmpty() -> R.string.score_subject_err
                else -> -1
            }
            if (error != -1) {
                notify(error)
                return@clickListener
            }

            // validate score number
            val scoreValue = scoreInput.text.toString().toDoubleOrNull()
            if (scoreValue === null || scoreValue < 0 || scoreValue > 10) {
                notify(R.string.score_score_err)
                return@clickListener
            }

            // store entry
            Score(nameValue, lastnameValue, gradeValue!!, subjectValue!!, scoreValue).store(uid) //
                .addOnFailureListener(::failureListener) //
                .addOnSuccessListener {
                    switchTo(
                        ListScoresActivity::class,
                        R.string.score_stored
                    )
                }
        }
    }

    private fun arrayAdapter(resource: Int): ArrayAdapter<CharSequence> {
        val adapter =
            ArrayAdapter.createFromResource(this, resource, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

    private var gradeValue: String? = null
    private var subjectValue: String? = null

    override fun onNothingSelected(parent: AdapterView<*>?) {
        when (parent?.id) {
            R.id.score_grade -> gradeValue = null
            R.id.score_subject -> subjectValue = null
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        when (parent?.id) {
            R.id.score_grade -> gradeValue = parent.getItemAtPosition(pos) as String
            R.id.score_subject -> subjectValue = parent.getItemAtPosition(pos) as String
        }
    }
}
