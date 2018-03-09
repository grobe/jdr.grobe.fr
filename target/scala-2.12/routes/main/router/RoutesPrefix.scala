// @GENERATOR:play-routes-compiler
// @SOURCE:G:/DEV/play2.6/jdr/conf/routes
// @DATE:Sun Mar 04 18:40:37 CET 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
