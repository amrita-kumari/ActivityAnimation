package com.kotlin.amritakumari.activityanimation

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.util.Log
import android.view.View
import de.hdodenhof.circleimageview.CircleImageView
import java.util.jar.Attributes

class ImageBehavior(val context: Context, val attrs: AttributeSet) : CoordinatorLayout.Behavior<CircleImageView>() {

    private var mCustomFinalHeight: Float
    private var mStartYPosition: Int = 0
    private var mFinalYPosition: Int = 0
    private var mStartHeight: Int = 0
    private var mStartXPosition: Int = 0
    private var mFinalXPosition: Int = 0
    private var mStartToolbarPosition: Float = 0f
    private var mChangeBehaviorPoint: Float = 0f

    init {
        mCustomFinalHeight = context.resources.getDimension(R.dimen.image_end_width)
        
    }
    override fun layoutDependsOn(parent: CoordinatorLayout?, child: CircleImageView?, dependency: View?): Boolean {
        return dependency is Toolbar
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: CircleImageView?, dependency: View?): Boolean {
        if(child != null && dependency != null) {
            initProperties(child, dependency)
            val maxScrollDistance = mStartToolbarPosition.toInt()
            val expandedPercentageFactor = dependency.getY() / maxScrollDistance

            if (expandedPercentageFactor < mChangeBehaviorPoint) {
                val heightFactor = (mChangeBehaviorPoint - expandedPercentageFactor) / mChangeBehaviorPoint

                val distanceXToSubtract = (mStartXPosition - mFinalXPosition) * heightFactor + child.getHeight() / 2
                val distanceYToSubtract = (mStartYPosition - mFinalYPosition) * (1f - expandedPercentageFactor) + child.getHeight() / 2

                child.setX(mStartXPosition - distanceXToSubtract)
                child.setY(mStartYPosition - distanceYToSubtract)

                val heightToSubtract = (mStartHeight - mCustomFinalHeight) * heightFactor

                val lp = child.getLayoutParams() as CoordinatorLayout.LayoutParams
                lp.width = (mStartHeight - heightToSubtract).toInt()
                lp.height = (mStartHeight - heightToSubtract).toInt()
                child.setLayoutParams(lp)
            } else {
                val distanceYToSubtract = (mStartYPosition - mFinalYPosition) * (1f - expandedPercentageFactor) + mStartHeight / 2

                child.setX((mStartXPosition - child.getWidth() / 2).toFloat())
                child.setY(mStartYPosition - distanceYToSubtract)

                val lp = child.getLayoutParams() as CoordinatorLayout.LayoutParams
                lp.width = mStartHeight
                lp.height = mStartHeight
                child.setLayoutParams(lp)
            }
        }
        return true
    }

    private fun initProperties(child: CircleImageView, dependency: View) {
        if (mStartYPosition == 0)
            mStartYPosition = dependency.y.toInt()

        if (mFinalYPosition == 0)
            mFinalYPosition = (dependency.height / 2)

        if (mStartHeight == 0)
            mStartHeight = child.height

        if (mStartXPosition == 0)
            mStartXPosition = (child.x + child.width / 2).toInt()

        if (mFinalXPosition == 0)
            mFinalXPosition = mCustomFinalHeight.toInt()

        if (mStartToolbarPosition == 0f)
            mStartToolbarPosition = dependency.y

        if (mChangeBehaviorPoint == 0f) {
            mChangeBehaviorPoint = (child.height - mCustomFinalHeight) / (2f * (mStartYPosition - mFinalYPosition))
        }
    }
}