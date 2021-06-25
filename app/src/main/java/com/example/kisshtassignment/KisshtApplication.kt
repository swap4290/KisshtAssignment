package com.example.kisshtassignment

import android.app.Application

class KisshtApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PhotoCredentials.init(
            this,
            "khD1lWUk6C-B9qrsYPj32Nfy5gQNWzBxdhitn2ue_po",
            "NN_i44B9mGStp1wSZaSUZypnNHjdRGiFl959CxT8mAk",
            20
        )
    }
}
