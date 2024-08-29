package com.roman.core_ui

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.removeAllDecoration(){
    while ( this.itemDecorationCount>0){
        this.removeItemDecorationAt(0)
    }
}
