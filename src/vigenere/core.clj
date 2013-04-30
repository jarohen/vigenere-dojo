(ns vigenere.core)

(def a-to-z (map char (range (int \a )  (inc (int \z)))) )

(def grid (take 26 (partition 26 1 (cycle a-to-z))))

(def grid-map 
  (zipmap a-to-z
          (map (fn [cypher-letters]
                 (zipmap a-to-z cypher-letters))
               grid)))

(defn encode-letter [letter key-letter]
  (get-in grid-map [key-letter letter]))

(defn encode-text [plaintext passphrase]
  (when (seq passphrase)
    (apply str (map encode-letter plaintext (cycle passphrase)))))

(encode-text "mytext" "key")

(def reversed-grid-map 
  (zipmap a-to-z
          (map (fn [cypher-letters]
                 (zipmap cypher-letters a-to-z))
               grid)))

(defn decode-letter [letter key-letter]
  (get-in reversed-grid-map [key-letter letter]))

(defn decode-text [plaintext passphrase]
  (when (seq passphrase)
    (apply str (map decode-letter plaintext (cycle passphrase)))))
 
(decode-text "wcrobr" "key")

(decode-text (encode-text "mytext" "key") "key")

(comment
  (map + [1 2 3] [3 10 5 6 9]))
