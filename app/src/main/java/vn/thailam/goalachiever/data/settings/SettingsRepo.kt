package vn.thailam.goalachiever.data.settings

interface ISettingsRepo {
    suspend fun shouldShowOnboard(): Boolean
}

class SettingsRepo : ISettingsRepo {
    override suspend fun shouldShowOnboard(): Boolean {
        return false
    }
}