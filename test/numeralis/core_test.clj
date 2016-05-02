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
  (is (= "one thousand and one" (number->english 1001)))
  (is (= "five thousand, two hundred and one" (number->english 5201)))
  (is (= "twenty-one thousand, five hundred and fifty-seven" (number->english 21557)))
  (is (= "seven hundred thousand and ten" (number->english 700010)))
  (is (= "one million and one" (number->english 1000001)))
  (is (= "one hundred million" (number->english 100000000)))
  (is (= "one billion" (number->english 1000000000)))
  (is (= "one billion, three hundred and forty-two million and twenty" (number->english 1342000020))))
