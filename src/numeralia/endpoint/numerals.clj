(ns numeralia.endpoint.numerals
  (:require [numeralia.core :refer [number->english]]
            [compojure.core :refer :all]
            [ring.util.response :refer [response status]]))

(defn numerals-endpoint [config]
  (GET "/numerals/:n" [n]
    (try
      (response {"number"  (Long/parseLong n)
                 "english" (number->english (Long/parseLong n))})
      (catch java.lang.NumberFormatException _e
        (-> (response {"error" "Invalid Number Format"})
            (status 400)))
      (catch java.lang.AssertionError _e
        (-> (response {"error" "Number is out of range"})
            (status 400))))))
