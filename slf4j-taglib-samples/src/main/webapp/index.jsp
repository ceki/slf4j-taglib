<%@ taglib prefix="log" uri="http://www.slf4j.org/taglib/tld" %>
<html>
<body>
<h1>SLF4J Taglib: manual tests</h1>

<p>Check log to see messages.</p>

<log:trace category="foo.bar">
  this is a trace message
</log:trace>

<log:debug category="foo.bar">
  this is a debug message
</log:debug>

<log:info category="foo.bar">
  this is an info message
</log:info>

<log:warn category="foo.bar">
  this is an warn message
</log:warn>

<log:error category="foo.bar">
  this is an error message
</log:error>

<p>About to dump attributes in application scope (using <code>dump</code> tag):</p>

<log:dump scope="application" />

</body>
</html>