package com.example.recyclerviewsnaphelper

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

class StartSnapHelper : LinearSnapHelper() {

    private var verticalHelper = null
    private var horizontalHelper = null

    // this is used to calculate the distance to snap
    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray? {

        val out = intArrayOf(0, 0)
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager))
        } else {
            out[0] = 0
        }

        if (layoutManager.canScrollVertically()){
            out[1] = distanceToStart(targetView, getVerticalHelper(layoutManager))
        } else {
            out[1] = 0
        }

        return out
    }

    // it will be called when the snap helper needs the view to be snapped
    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {

        if (layoutManager is LinearLayoutManager) {
            return if (layoutManager.canScrollHorizontally()) {
                getstartView(layoutManager, getHorizontalHelper(layoutManager))
            } else {
                getstartView(layoutManager, getVerticalHelper(layoutManager))
            }
        }
        return super.findSnapView(layoutManager)
    }

    private fun distanceToStart(targetView: View, helper: OrientationHelper): Int {
        return helper.getDecoratedStart(targetView) - helper.startAfterPadding
    }

    private fun getstartView(
        layoutManager: RecyclerView.LayoutManager,
        helper: OrientationHelper
    ): View? {
        if (layoutManager is LinearLayoutManager) {
            val firstChild = layoutManager.findFirstVisibleItemPosition()

            val isLastItem =
                layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1

            if (firstChild == RecyclerView.NO_POSITION || isLastItem) return null

            val child = layoutManager.findViewByPosition(firstChild)

            if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2
                && helper.getDecoratedEnd(child) > 0
            ) {
                return child
            } else if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1) {
                return null
            } else {
                return layoutManager.findViewByPosition(firstChild + 1)
            }
        }

        return super.findSnapView(layoutManager)
    }

    fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        return verticalHelper ?: OrientationHelper.createVerticalHelper(layoutManager)
    }

    fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        return horizontalHelper ?: OrientationHelper.createHorizontalHelper(layoutManager)
    }
}