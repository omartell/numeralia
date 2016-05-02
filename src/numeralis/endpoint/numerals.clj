(ns numeralis.endpoint.numerals
  (:require [numeralis.core :refer [number->english]]
            [compojure.core :refer :all]
            [ring.util.response :refer [response status]]))

(defn numerals-endpoint [config]
  (GET "/numerals/:n" [n]
    (try
      (response {"number"  (Long/parseLong n)
                 "english" (number->english (Long/parseLong n))})
      (catch java.lang.NumberFormatException _e
        (-> (response {"error" "Invalid Number. Must be within zero and one billion."})
            (status 400))))))
