package vcmsa.ci.androidapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import vcmsa.ci.androidapp.R

class MainActivity : AppCompatActivity() {

    private val songTitles = ArrayList<String>()
    private val artistNames = ArrayList<String>()
    private val ratings = ArrayList<Int>()
    private val comments = ArrayList<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songInput = findViewById<EditText>(R.id.songTitleInput)
        val artistInput = findViewById<EditText>(R.id.artistInput)
        val ratingInput = findViewById<EditText>(R.id.ratingInput)
        val commentInput = findViewById<EditText>(R.id.commentInput)

        val addButton = findViewById<Button>(R.id.addButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        addButton.setOnClickListener {
            val title = songInput.text.toString()
            val artist = artistInput.text.toString()
            val rating = ratingInput.text.toString().toIntOrNull()
            val comment = commentInput.text.toString()

            if (title.isNotEmpty() && artist.isNotEmpty() && rating != null && rating in 1..5 && comment.isNotEmpty()) {
                songTitles.add(title)
                artistNames.add(artist)
                ratings.add(rating)
                comments.add(comment)
                songInput.text.clear()
                artistInput.text.clear()
                ratingInput.text.clear()
                commentInput.text.clear()
                Toast.makeText(this, "Song added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Enter valid input", Toast.LENGTH_SHORT).show()
            }
        }

        nextButton.setOnClickListener {
            val intent = Intent(this, DetailedActivity::class.java)
            intent.putStringArrayListExtra("songs", songTitles)
            intent.putStringArrayListExtra("artists", artistNames)
            intent.putIntegerArrayListExtra("ratings", ratings)
            intent.putStringArrayListExtra("comments", comments)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}
