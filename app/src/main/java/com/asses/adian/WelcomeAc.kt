package com.asses.adian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.os.Bundle
import android.os.Build
import android.view.View
import android.text.Html
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import android.view.WindowManager
import android.graphics.Color
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.asses.adian.helpers.PrefManager

class WelcomeAc : AppCompatActivity() {
    private var viewPager: ViewPager? = null
    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    private var dotsLayout: LinearLayout? = null
    private var dots: Array<TextView?>? = null
    private var layouts: IntArray? = null
    private var btnSkip: TextView? = null
    private var btnNext: Button? = null
    private var prefManager: PrefManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Checking for first time launch - before calling setContentView()
        prefManager = PrefManager(this)
        if (!prefManager!!.isFirstTimeLaunch) {
            launchHomeScreen()
            finish()
        }
        // Making notification bar transparent
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_welcome)
        viewPager = findViewById<View>(R.id.view_pager) as ViewPager
        dotsLayout = findViewById<View>(R.id.layoutDots) as LinearLayout
        btnSkip = findViewById<View>(R.id.btn_skip) as TextView
        btnNext = findViewById<View>(R.id.btn_next) as Button

        val btnLogin = findViewById<View>(R.id.btn_login)

        btnLogin.setOnClickListener {
            Toast.makeText(
                this,
                "Login Button has been clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = intArrayOf(
            R.layout.welcome_one,
            R.layout.welcome_two,
            R.layout.welcome_three
        )
        // adding bottom dots
        addBottomDots(0)
        // making notification bar transparent
        changeStatusBarColor()
        myViewPagerAdapter = MyViewPagerAdapter()
        viewPager!!.adapter = myViewPagerAdapter
        viewPager!!.addOnPageChangeListener(viewPagerPageChangeListener)
        btnSkip!!.setOnClickListener { launchHomeScreen() }
        btnNext!!.setOnClickListener {
            // if last page home screen will be launched
            val current = getItem(+1)
            if (current < layouts!!.size) {
                // move to next screen
                viewPager!!.currentItem = current
            } else {
                launchHomeScreen()
            }
        }
    }

    // button dots
    private fun addBottomDots(currentPage: Int) {
        dots = arrayOfNulls(layouts!!.size)
        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)
        dotsLayout!!.removeAllViews()
        for (i in dots!!.indices) {
            dots!![i] = TextView(this)
            dots!![i]!!.text = Html.fromHtml("&#8226;")
            dots!![i]!!.textSize = 35f
            dots!![i]!!.setTextColor(colorsInactive[currentPage])
            dotsLayout!!.addView(dots!![i])
        }
        if (dots!!.size > 0) dots!![currentPage]!!.setTextColor(colorsActive[currentPage])
    }

    // get items
    private fun getItem(i: Int): Int {
        return viewPager!!.currentItem + i
    }
    private fun launchHomeScreen() {
        prefManager!!.isFirstTimeLaunch = true
            Toast.makeText(
                this,
                "Oops that's the last slide, no homepage",
                Toast.LENGTH_SHORT
            ).show()
//        startActivity(Intent(this@WelcomeAc, MainActivity::class.java))
//        finish()
    }

    //  viewpager change listener
    var viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            addBottomDots(position)
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts!!.size - 1) {
                // last page. make button text to GOT IT
                btnNext!!.text = getString(R.string.start)
                btnSkip!!.visibility = View.GONE
            } else {
                // still pages are left
                btnNext!!.text = getString(R.string.next)
                btnSkip!!.visibility = View.VISIBLE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    /**
     * Making notification bar transparent
     */
    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * View pager adapter
     */
    inner class MyViewPagerAdapter : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater!!.inflate(layouts!![position], container, false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return layouts!!.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
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