# clj-github-issue-labels

[![Build Status](https://travis-ci.org/dorzey/clj-github-issues.svg?branch=master)](https://travis-ci.org/dorzey/clj-github-issues)

Consistent labels are used across multiple GitHub repositories for issues and pull requests. This tool provides a convenient way to get a view across all the repositories that are of interest to you.

Usage
-

Running

```
lein run issue
```

will produce output like below.

```
|                         bug |                 enhancement |                   duplicate |
|-----------------------------+-----------------------------+-----------------------------+
| /clj-github-issues/issues/1 |                             |                             |
|                             | /clj-github-issues/issues/3 |                             |
|                             | /clj-github-issues/issues/2 |                             |
|                             |                             | /clj-github-issues/issues/3 |
|                             |                             |                             |
```
