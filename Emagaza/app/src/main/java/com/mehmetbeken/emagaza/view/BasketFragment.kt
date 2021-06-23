package com.mehmetbeken.emagaza.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mehmetbeken.emagaza.R
import com.mehmetbeken.emagaza.adapter.BasketRecyclerAdapter
import com.mehmetbeken.emagaza.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_basket.*


class BasketFragment : Fragment() {
    private val productViewModel:ProductViewModel by activityViewModels()
    private var basketRecyclerAdapter:BasketRecyclerAdapter?=null

    private val swipeCallback =object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
           return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition=viewHolder.layoutPosition
            if (basketRecyclerAdapter!=null){
                val selectedProduct=basketRecyclerAdapter!!.basketList.get(layoutPosition)
                productViewModel.deleteProductFromBasket(selectedProduct)
                basketRecyclerAdapter!!.notifyDataSetChanged()

            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        basketRecyclerView.layoutManager=LinearLayoutManager(activity?.baseContext)
        ItemTouchHelper(swipeCallback).attachToRecyclerView(basketRecyclerView)
        productViewModel.basket.observe(viewLifecycleOwner, Observer {
            basketRecyclerAdapter=BasketRecyclerAdapter(it)
            basketRecyclerView.adapter=basketRecyclerAdapter


        })
        productViewModel.totalBasket.observe(viewLifecycleOwner, Observer {
            totalBasketText.text="Toplam Sepet: ${it}"
        })
    }
}