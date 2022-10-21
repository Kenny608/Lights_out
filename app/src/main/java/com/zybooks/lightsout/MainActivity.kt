package com.zybooks.lightsout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.zybooks.lightsout.databinding.ActivityMainBinding

const val GAME_STATE = "gameState"
class MainActivity : AppCompatActivity() {

    private lateinit var game: LightsOutGame
    private lateinit var lightGridLayout: GridLayout
    private var lightOnColor = 0
    private var lightOffColor = 0
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lightGridLayout = findViewById(R.id.light_grid)

        // Add the same click handler to all grid buttons
        for (gridButton in lightGridLayout.children) {
            gridButton.setOnClickListener(this::onLightButtonClick)
        }

        lightOnColor = ContextCompat.getColor(this, R.color.yellow)
        lightOffColor = ContextCompat.getColor(this, R.color.black)

        game = LightsOutGame()
        if (savedInstanceState == null) {
            startGame()
        } else {
            game.state = savedInstanceState.getString(GAME_STATE)!!
            setButtonColors()
        }

    binding.newGameButton.setOnClickListener { _ -> startGame() }
        binding.gameOver.setOnClickListener { _ ->
            val gameOverIntent = Intent(this, GameOver::class.java)
            val isGameOver = game.isGameOver
            startActivity(gameOverIntent)
        }

        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(GAME_STATE, game.state)
    }

    private fun startGame() {
        game.newGame()
        setButtonColors()
    }

    private fun onLightButtonClick(view: View) {

        // Find the button's row and col
        val buttonIndex = lightGridLayout.indexOfChild(view)
        val row = buttonIndex / GRID_SIZE
        val col = buttonIndex % GRID_SIZE

        game.selectLight(row, col)
        setButtonColors()

        // Congratulate the user if the game is over
        if (game.isGameOver) {
            Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setButtonColors() {

        // Set all buttons' background color
        for (buttonIndex in 0 until lightGridLayout.childCount) {
            val gridButton = lightGridLayout.getChildAt(buttonIndex)

            // Find the button's row and col
            val row = buttonIndex / GRID_SIZE
            val col = buttonIndex % GRID_SIZE

            if (game.isLightOn(row, col)) {
                gridButton.setBackgroundColor(lightOnColor)
            } else {
                gridButton.setBackgroundColor(lightOffColor)
            }
        }
    }


}