package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.main

import jp.ne.keisuke.kiuchi.devicekanrisan.data.entity.UserEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.BasePresenter
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.BaseView

interface MainContract {

    interface View : BaseView {
        fun loaded(userEntity: UserEntity)
        fun logouted(userEntity: UserEntity)
    }

    interface Presenter : BasePresenter {
        fun load()
        fun logout()
    }
}
