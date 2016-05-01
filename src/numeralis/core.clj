(ns numeralis.core)

(def base-mappings
  {0  "zero"
   1  "one"
   2  "two"
   3  "three"
   4  "four"
   5  "five"
   6  "six"
   7  "seven"
   8  "eight"
   9  "nine"
   10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"
   20 "twenty"
   30 "thirty"
   40 "forty"
   50 "fifty"
   60 "sixty"
   70 "seventy"
   80 "eighty"
   90 "ninety"})

(def magnitudes
  {100  "hundred"
   1000 "thousand"})

(defn number->english [original-number]
  (if-let [predefined (get base-mappings original-number)]
    predefined
    (loop [current-number original-number
           sentence       ""]
      (if-let [predefined (get base-mappings current-number)]
        (if (> current-number 0)
          (str sentence predefined)
          sentence)
        (let [order-of-magnitude     (int (java.lang.Math/pow 10 (int (java.lang.Math/log10 current-number))))
              most-significant-digit (int (/ current-number order-of-magnitude))
              most-significant-value (* most-significant-digit order-of-magnitude)
              remaining-value        (- current-number most-significant-value)]
          (case order-of-magnitude
            10 (str sentence
                    (get base-mappings most-significant-value)
                    "-"
                    (get base-mappings remaining-value))
            (recur remaining-value
                   (str sentence
                        (get base-mappings most-significant-digit)
                        " "
                        (get magnitudes order-of-magnitude)
                        (when (and (< remaining-value 100)
                                   (> remaining-value 0))
                          " and ")))))))))
