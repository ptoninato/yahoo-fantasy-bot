/*
 * MIT License
 *
 * Copyright (c) 2020 Landon Patmore
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.landonpatmore.yahoofantasybot.shared.database.models

data class Alert(
    val type: Int, // TODO: Map these to actual alerts
    val hour: Int,
    val minute: Int,
    val startMonth: Int,
    val endMonth: Int,
    val dayOfWeek: Int,
    val uuid: String = "${type}${hour}${minute}${startMonth}${endMonth}${dayOfWeek}"
//    val timeZone: String
) {
    companion object {
        const val SCORE = 0
        const val CLOSE_SCORE = 1
        const val STANDINGS = 2
        const val MATCHUP = 3
    }
}