package com.tayfuncesur.mobile

import com.tayfuncesur.mobile.ui.detail.DetailActivityTest
import com.tayfuncesur.mobile.ui.main.MainActivityTest
import org.junit.runners.Suite
import org.junit.runner.RunWith


@RunWith(Suite::class)
@Suite.SuiteClasses(MainActivityTest::class, DetailActivityTest::class)
class RunAllUITests