(require 'cljs.build.api)
(require 'zhuli.manage)


(zhuli.manage/watch-project
  (fn []
      (cljs.build.api/build "src"
        {:main 'hello-world.core
         :output-to "out/main.js"})))


