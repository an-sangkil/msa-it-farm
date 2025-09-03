import { createI18n } from 'vue-i18n'

function loadLocaleMessages () {
  const locales = import.meta.glob('./locales/*.json', { eager: true })
  const messages = {}
  
  Object.keys(locales).forEach(key => {
    const matched = key.match(/\.\/locales\/([A-Za-z0-9-_]+)\.json$/i)
    if (matched && matched.length > 1) {
      const locale = matched[1]
      messages[locale] = locales[key].default || locales[key]
    }
  })
  return messages
}

export default createI18n({
  legacy: false,
  locale: import.meta.env.VITE_I18N_LOCALE || 'en',
  fallbackLocale: import.meta.env.VITE_I18N_FALLBACK_LOCALE || 'en',
  messages: loadLocaleMessages()
})
