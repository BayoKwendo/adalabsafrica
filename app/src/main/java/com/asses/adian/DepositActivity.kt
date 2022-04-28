package com.asses.adian

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.asses.adian.databinding.ActivityDepositBinding

class DepositActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDepositBinding

    // numbers varible
    var numberOne: Int? = 0
    var numberTwo: Int? = 0
    var numberThree: Int? = 0
    var numberFour: Int? = 0
    var numberFive: Int? = 0
    var numberaSix: Int? = 0
    var numberaSeven: Int? = 0
    var numberEight: Int? = 0
    var numberNine: Int? = 0
    var numberZero: Int? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDepositBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        binding.debit.setOnClickListener {
            Toast.makeText(
                this,
                "Chase debit has been clieck",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.myAccount.setOnClickListener {
            Toast.makeText(
                this,
                "My Account has been click",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.submit.setOnClickListener {
            Toast.makeText(
                this,
                "Submit button clicked",
                Toast.LENGTH_SHORT
            ).show()
        }

//        binding.result.text = textView.toString()
        var textView: String? =""
        binding.btnOne.setOnClickListener {
            textView = "$textView"+"1"
            binding.btnOne.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()


        }

        binding.btnTwo.setOnClickListener {
            textView = "$textView"+"2"
            binding.btnTwo.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()

        }

        binding.btnThree.setOnClickListener {
            textView = "$textView"+"3"
            binding.btnThree.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()

        }

        binding.btnFour.setOnClickListener {
            textView = "$textView"+"4"
            binding.btnFour.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()

        }

        binding.btnFive.setOnClickListener {
            textView = "$textView"+"5"
            binding.btnFive.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()

        }

        binding.btnSix.setOnClickListener {
            textView = "$textView"+"6"
            binding.btnSix.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()

        }

        binding.btnSeven.setOnClickListener {
            textView = "$textView"+"7"
            binding.btnSeven.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()

        }

        binding.btnEight.setOnClickListener {
            textView = "$textView"+8
            binding.btnEight.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()

        }

        binding.btnNine.setOnClickListener {
            textView = "$textView"+"9"
            binding.btnNine.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()

        }

        binding.btnZero.setOnClickListener {
            textView = "$textView"+"0"
            binding.btnZero.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()

        }


        binding.btnDot.setOnClickListener {
            textView = "$textView."
            binding.btnDot.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()

        }


        binding.clearText.setOnClickListener {
            textView = ""
            binding.btnDot.setBackgroundResource(R.drawable.button_calculator_pressed)
            binding.result.text = textView.toString()
        }
    }

    // back pressed
    override fun onSupportNavigateUp(): Boolean {
      onBackPressed()
        return true
    }
    override fun onBackPressed() {
        this.startActivity(Intent(this, HomeActivity::class.java))
    }

}