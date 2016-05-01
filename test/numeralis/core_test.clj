(ns numeralis.core-test
  (:require [clojure.test :refer :all]
            [numeralis.core :refer :all]))

(deftest test-number->english-phrase
  (is (= "zero" (number->english-phrase 0)))
  (is (= "one"  (number->english-phrase 1)))
  (is (= "ten"  (number->english-phrase 10)))
  (is (= "twenty" (number->english-phrase 20)))
  (is (= "twenty-one" (number->english-phrase 21)))
  (is (= "ninety-nine" (number->english-phrase 99)))
  (is (= "one hundred" (number->english-phrase 100)))
  (is (= "one hundred and eleven" (number->english-phrase 111)))
  (is (= "one hundred and twenty" (number->english-phrase 120)))
  (is (= "one hundred and twenty-one" (number->english-phrase 121)))
  (is (= "one thousand" (number->english-phrase 1000)))
  (is (= "one thousand and one" (number->english-phrase 1001))))
