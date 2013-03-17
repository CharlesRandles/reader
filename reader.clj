; -*-Lisp-*-

;;;;
;;;;Parse an OPML fole for atom/rss feeds
;;;;

(require '[clojure.xml :as xml]
         '[clojure.zip :as zip])
 
;;convenience function, first sawn at nakkaya.com later in clj.zip src
(defn zip-str [s]
  (zip/xml-zip (xml/parse (java.io.ByteArrayInputStream. (.getBytes s)))))
 
;;parse from xml-strings to internal xml representation
(zip-str "<a href='nakkaya.com'/>")

;;[{:tag :a, :attrs {:href "nakkaya.com"}, :content nil} nil]
 
;;root can be rendered with xml/emit-element
(xml/emit-element (zip/root [{:tag :a, :attrs {:href "nakkaya.com"}, :content nil} nil]))

;;<a href='nakkaya.com'/>
;;printed (to assure it's not lazy and performance), can be catched to string variable with with-out-str

(for [arg *command-line-args*]
	(println arg))