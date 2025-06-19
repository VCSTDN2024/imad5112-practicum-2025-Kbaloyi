package vcmsa.ci.androidapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import vcmsa.ci.androidapp.R

class DetailedActivity : AppCompatActivity() {

    private lateinit var songs: ArrayList<String>
    private lateinit var artists: ArrayList<String>
    private lateinit var ratings: ArrayList<Int>
    private lateinit var comments: ArrayList<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        songs = intent.getStringArrayListExtra("songs") ?: ArrayList()
        artists = intent.getStringArrayListExtra("artists") ?: ArrayList()
        ratings = intent.getIntegerArrayListExtra("ratings") ?: ArrayList()
        comments = intent.getStringArrayListExtra("comments") ?: ArrayList()

        val listView = findViewById<TextView>(R.id.songListText)
        val avgText = findViewById<TextView>(R.id.avgRatingText)
        val showButton = findViewById<Button>(R.id.showSongsButton)
        val avgButton = findViewById<Button>(R.id.avgButton)
        val backButton = findViewById<Button>(R.id.backButton)

        showButton.setOnClickListener {
            var list = ""
            for (i in songs.indices) {
                list += "Song: ${songs[i]}, Artist: ${artists[i]}, Rating: ${ratings[i]}, Comment: ${comments[i]}\n\n"
            }
            listView.text = list
        }

        avgButton.setOnClickListener {
            if (ratings.isNotEmpty()) {
                var total = 0
                for (r in ratings) total += r
                val avg = total.toDouble() / ratings.size
                avgText.text = "Average Rating: $avg"
            } else {
                avgText.text = "No ratings available"
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
