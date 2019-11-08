package com.chao.ycdownload

/**
 * Date: 2019/8/1 10:51
 * Author: hans yang
 * Description:
 */
enum class DownloadState(val value: Int) {

    START_WAIT(0),
    START_RUN(1),
    STOP_PAUSE(2),
    STOP_FINISHED(3),
    STOP_ERROR(4);
}