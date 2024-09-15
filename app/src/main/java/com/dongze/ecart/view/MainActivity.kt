package com.dongze.ecart.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dongze.ecart.R
import com.dongze.ecart.databinding.ActivityMainBinding
import com.dongze.ecart.model.remote.dashboard.ProductDetail
import com.dongze.ecart.view.cart.CartFragment
import com.dongze.ecart.view.category.CategoryFragment
import com.dongze.ecart.view.category.ProductListFragment
import com.dongze.ecart.view.category.SubCategoryFragment
import com.dongze.ecart.view.dashboard.DashboardFragment
import com.dongze.ecart.view.dashboard.ProductDetailFragment
import com.dongze.ecart.view.profile.ProfileFragment

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dashboardFrag: DashboardFragment
    private lateinit var categoryFrag: CategoryFragment
    private lateinit var cartFrag: CartFragment
    private lateinit var profileFrag: ProfileFragment
    private lateinit var subCatFrag: SubCategoryFragment
    private lateinit var pDetailFrag: ProductDetailFragment
    private lateinit var pListFrag: ProductListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        dashboardFrag = DashboardFragment()
        categoryFrag = CategoryFragment()
        cartFrag = CartFragment()
        profileFrag = ProfileFragment()
        subCatFrag = SubCategoryFragment()
        pDetailFrag = ProductDetailFragment()
        pListFrag = ProductListFragment()

        navToFrag(dashboardFrag)

        binding.bottomNav.setOnNavigationItemSelectedListener {
            menuItem->
            when(menuItem.itemId){
                R.id.opt_home -> navToFrag(dashboardFrag)
                R.id.opt_category -> navToFrag(categoryFrag)
                R.id.opt_cart -> navToFrag(cartFrag)
                R.id.opt_profile -> navToFrag(profileFrag)
            }
            true
        }
    }

    private fun navToFrag(frag: Fragment) =
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, frag)
            .commit()

    override fun sendCid(cid: String){

        supportFragmentManager.beginTransaction() //ensure refresh
            .detach(subCatFrag)
            .attach(subCatFrag)
            .commit()

        subCatFrag.receiveCid(cid)
        navToFrag(subCatFrag)
    }

    override fun sendPid(pid: String) {
        pDetailFrag.receivePid(pid)
        navToFrag(pDetailFrag)
    }

    override fun sendPList(plist: List<ProductDetail>) {
        pListFrag.receivePList(plist)
        navToFrag(pListFrag)
    }
}