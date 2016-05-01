(ns numeralis.core-test
  (:require [clojure.test :refer :all]
            [numeralis.core :refer :all]))

(deftest test-number->english
  (is (= "zero" (number->english 0)))
  (is (= "one"  (number->english 1)))
  (is (= "ten"  (number->english 10)))
  (is (= "twenty" (number->english 20)))
  (is (= "twenty-one" (number->english 21)))
  (is (= "ninety-nine" (number->english 99)))
  (is (= "one hundred" (number->english 100)))
  (is (= "one hundred and eleven" (number->english 111)))
  (is (= "one hundred and twenty" (number->english 120)))
  (is (= "one hundred and twenty-one" (number->english 121)))
  (is (= "one thousand" (number->english 1000)))
  (is (= "one thousand and one" (number->english 1001))))
