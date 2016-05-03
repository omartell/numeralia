(ns numeralia.core)

(def ^:private english-numbers
  {1          "one"
   2          "two"
   3          "three"
   4          "four"
   5          "five"
   6          "six"
   7          "seven"
   8          "eight"
   9          "nine"
   10         "ten"
   11         "eleven"
   12         "twelve"
   13         "thirteen"
   14         "fourteen"
   15         "fifteen"
   16         "sixteen"
   17         "seventeen"
   18         "eighteen"
   19         "nineteen"
   20         "twenty"
   30         "thirty"
   40         "forty"
   50         "fifty"
   60         "sixty"
   70         "seventy"
   80         "eighty"
   90         "ninety"
   100        "hundred"
   1000       "thousand"
   1000000    "million"
   1000000000 "billion"})

(defn number->english [number]
  {:pre [(and (>= number 0) (< number (Math/pow 10 12)))]}
  "Given a number between zero and one trillion, translate it into British English"
  (if (zero? number)
    "zero"
    (->> (keys english-numbers)
         (filter #(<= % number))
         (sort #(compare %2 %1))
         (reduce (fn [[current sentence] predefined]
                   (let [times          (int (/ current predefined))
                         left           (mod current predefined)
                         english-number (get english-numbers predefined)
                         new-sentence   (when (> times 0)
                                          (condp > current
                                            20               english-number
                                            100              (str english-number (when (> left 0) "-"))
                                            (Math/pow 10 12) (str (number->english times)
                                                                  " "
                                                                  english-number
                                                                  (cond
                                                                    (and (< left 100) (> left 0)) " and "
                                                                    (> left 0)                    ", "))))]
                     [left (str sentence new-sentence)]))
                 [number ""])
         last)))
