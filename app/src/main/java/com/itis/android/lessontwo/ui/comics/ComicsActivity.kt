package com.itis.android.lessontwo.ui.comics

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.itis.android.lessontwo.R
import com.itis.android.lessontwo.model.comics.Comics
import com.itis.android.lessontwo.repository.RepositoryProvider
import com.itis.android.lessontwo.ui.base.BaseActivity
import com.itis.android.lessontwo.utils.Constants.ID_KEY
import com.itis.android.lessontwo.utils.Constants.NAME_KEY
import com.itis.android.lessontwo.utils.ImageLoadHelper

class ComicsActivity : BaseActivity(), ComicsView {

    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var toolbar: Toolbar
    private lateinit var ivCover: ImageView
    private lateinit var tvDescription: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvPages: TextView
    private lateinit var progressBar: ProgressBar

    private var id: Long = 0

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: ComicsPresenter

    @ProvidePresenter
    fun initPresenter(): ComicsPresenter =
            ComicsPresenter(RepositoryProvider.provideComicsRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentFrameLayout = findViewById<FrameLayout>(R.id.container)
        layoutInflater.inflate(R.layout.activity_comics, contentFrameLayout)
        initViews()
        id = intent.getLongExtra(ID_KEY, 0)
    }

    override fun getComicsId() = presenter.init(id)

    override fun handleError(error: Throwable) =
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()

    override fun setPageCount(comics: Comics) {
        tvPages.text = comics.pageCount.toString()
    }

    override fun setPrice(comics: Comics) {
        if (comics.prices.isNotEmpty()) {
            tvPrice.text = getString(R.string.price_format, comics.prices[0]?.price.toString())
        }
    }

    override fun setDescription(comics: Comics) {
        if (comics.textObjects != null) {
            val description = StringBuilder()
            for (comicsTextObject in comics.textObjects) {
                description.append(comicsTextObject.text).append("\n")
            }
            tvDescription.text = if (description.isNotEmpty())
                description.toString().trim { it <= ' ' }
            else
                getString(R.string.text_desc_not_found)
        }
    }

    override fun setImage(comics: Comics) {
        if (comics.image != null) {
            ImageLoadHelper.loadPicture(ivCover, String.format("%s.%s", comics.image.path,
                    comics.image.extension))
        } else {
            ImageLoadHelper.loadPictureByDrawable(ivCover, R.drawable.image_error_marvel_logo)
        }
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun initViews() {
        findViews()
        supportActionBar(toolbar)
        setBackArrow(toolbar)
        collapsingToolbar.title = intent.getStringExtra(NAME_KEY)
    }

    private fun findViews() {
        collapsingToolbar = findViewById(R.id.ct_comics)
        toolbar = findViewById(R.id.tb_comics)
        ivCover = findViewById(R.id.iv_comics)
        tvDescription = findViewById(R.id.tv_description)
        tvPrice = findViewById(R.id.tv_price)
        tvPages = findViewById(R.id.tv_pages)
        progressBar = findViewById(R.id.progress_loader)
    }

    companion object {

        @JvmStatic
        fun start(activity: Activity, comics: Comics) {
            val intent = Intent(activity, ComicsActivity::class.java)
            intent.putExtra(NAME_KEY, comics.name)
            intent.putExtra(ID_KEY, comics.id)
            activity.startActivity(intent)
        }
    }
}
