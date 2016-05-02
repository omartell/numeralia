(ns numeralis.endpoint.numerals-test
  (:require [numeralis.endpoint.numerals :refer [numerals-endpoint]]
            [ring.mock.request :as mock]
            [clojure.test :refer :all]))

(def handler
  (numerals-endpoint {}))

(deftest test-numerals-endpoint
  (testing "returns a successful representation when the number can be translated"
    (is (= {:status  200
            :headers {}
            :body    {"number"  1
                      "english" "one"}}
           (handler (mock/request :get "/numerals/1")))))
  (testing "returns a bad request response when the number can't be translated"
    (is (= 400
           (-> (handler (mock/request :get "/numerals/foobar"))
               :status)))))
