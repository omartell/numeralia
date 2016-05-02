(ns numeralis.system
  (:require [clojure.java.io :as io]
            [com.stuartsierra.component :as component]
            [duct.component.endpoint :refer [endpoint-component]]
            [duct.component.handler :refer [handler-component]]
            [duct.middleware.route-aliases :refer [wrap-route-aliases]]
            [meta-merge.core :refer [meta-merge]]
            [ring.component.jetty :refer [jetty-server]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.format :refer [wrap-restful-format]]
            [numeralis.endpoint.numerals :refer [numerals-endpoint]]))

(def base-config
  {:app {:middleware [[wrap-restful-format]
                      [wrap-defaults :defaults]
                      [wrap-route-aliases :aliases]]
         :defaults  (meta-merge site-defaults {:static {:resources "numeralis/public"}})
         :aliases    {"/" "/index.html"}}})

(defn new-system [config]
  (let [config (meta-merge base-config config)]
    (-> (component/system-map
         :app  (handler-component (:app config))
         :http (jetty-server (:http config))
         :example (endpoint-component numerals-endpoint))
        (component/system-using
         {:http [:app]
          :app  [:example]
          :example []}))))
