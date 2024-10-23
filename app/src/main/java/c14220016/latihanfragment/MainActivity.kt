package c14220016.latihanfragment

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mFragmentManager = supportFragmentManager
        val mfGame = fgame();

        mFragmentManager.findFragmentByTag(fgame::class.java.simpleName)
        mFragmentManager
            .beginTransaction()
            .add(R.id.mainframe, mfGame, fgame::class.java.simpleName)
            .commit()

//        Navigation Logic
        val _buttonToGame = findViewById<Button>(R.id.toGameButton)
        val _buttonToScore = findViewById<Button>(R.id.toScoreButton)
        val _buttonToConfig = findViewById<Button>(R.id.toConfigButton)

        _buttonToGame.setOnClickListener {
            val mfGame = fgame()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.mainframe, mfGame, fgame::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        _buttonToScore.setOnClickListener {
            val mfScore = fscore()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.mainframe, mfScore, fscore::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        _buttonToConfig.setOnClickListener {
            val mfConfig = fconfig()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.mainframe, mfConfig, fconfig::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }
}