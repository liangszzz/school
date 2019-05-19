package com.github.yiyan1992.carloan.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetUtil {

  public static Set listToSet(List list) {
    Set set = new HashSet();
    set.addAll(list);
    return set;
  }
}
