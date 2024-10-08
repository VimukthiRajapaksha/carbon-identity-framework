/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.application.mgt.dao;

import org.wso2.carbon.identity.application.common.IdentityApplicationManagementException;
import org.wso2.carbon.identity.application.common.model.AuthorizationDetailsType;
import org.wso2.carbon.identity.application.common.model.AuthorizedAPI;
import org.wso2.carbon.identity.application.common.model.AuthorizedScopes;
import org.wso2.carbon.identity.application.common.model.Scope;

import java.util.Collections;
import java.util.List;

/**
 * Authorized API DAO interface.
 */
public interface AuthorizedAPIDAO {

    @Deprecated
    void addAuthorizedAPI(String applicationId, String apiId, String policyId, List<Scope> scopes,
                          int tenantId)
            throws IdentityApplicationManagementException;

    List<AuthorizedAPI> getAuthorizedAPIs(String applicationId, int tenantId)
            throws IdentityApplicationManagementException;

    @Deprecated
    void patchAuthorizedAPI(String appId, String apiId, List<String> addedScopes,
                            List<String> removedScopes, int tenantId)
            throws IdentityApplicationManagementException;

    void deleteAuthorizedAPI(String appId, String apiId, int tenantId)
            throws IdentityApplicationManagementException;

    List<AuthorizedScopes> getAuthorizedScopes(String applicationId, int tenantId)
            throws IdentityApplicationManagementException;

    AuthorizedAPI getAuthorizedAPI(String appId, String apiId, int tenantId)
            throws IdentityApplicationManagementException;

    default void addAuthorizedAPI(String applicationId, AuthorizedAPI authorizedAPI, int tenantId)
            throws IdentityApplicationManagementException {
    }

    default void patchAuthorizedAPI(String appId, String apiId, List<String> scopesToAdd, List<String> scopesToRemove,
            List<String> authorizationDetailsTypesToAdd, List<String> authorizationDetailsTypesToRemove, int tenantId)
            throws IdentityApplicationManagementException {
    }

    default List<AuthorizationDetailsType> getAuthorizedAuthorizationDetailsTypes(String applicationId, int tenantId)
            throws IdentityApplicationManagementException {

        return Collections.emptyList();
    }
}
