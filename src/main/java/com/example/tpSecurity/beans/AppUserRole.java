package com.example.tpSecurity.beans;


import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.tpSecurity.beans.AppUserPermission.USER_READ;
import static com.example.tpSecurity.beans.AppUserPermission.USER_WRITE;

public enum AppUserRole {
  USER(Sets.newHashSet(USER_READ)),
  ADMIN(Sets.newHashSet(USER_READ,USER_WRITE));
  private final Set<AppUserPermission> permissions;

  AppUserRole(Set<AppUserPermission> permissions) {
    this.permissions = permissions;
  }
}
