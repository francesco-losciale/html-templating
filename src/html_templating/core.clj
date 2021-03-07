(ns html-templating.core
  (:require [selmer.parser :as selmer]
            [selmer.filters :as filters]
            [selmer.middleware :refer [wrap-error-page]]))

(defn renderer []
  (wrap-error-page
    (fn [template]
      {:status 200
       :body (selmer/render-file template {})})))

; (selmer/render-file "index.html" {:name "World" :items (range 10) :user {:first "Francesco" :last "Losciale"}})

; (filters/add-filter! :empty? empty?)
; (selmer/render "{% if files|empty? %} no files{% else %} files{% endif %}" {:files []})




; you should always escape user inputs, because it can be malicious. Only server content can be unescaped.
; by default, Selmer escapes content but with a filter you can override this mechanism.
; Try this:

; (selmer/render "{{x}}" {:x "<div>I'm safe</div>"}) ; it returns escaped html

; then add filter
;(filters/add-filter! :foo
;                     (fn [x] [:safe (.toUpperCase x)]))

; and try this:
; (selmer/render "{{x|foo}}" {:x "<div>I'm safe</div>"})




; caveat... templates are moemoized by default. Final version of the template will be compiled and kept in memory.
; use (selmer/cache-off!) or (selmer/cache-on!) to switch off or on this feature.