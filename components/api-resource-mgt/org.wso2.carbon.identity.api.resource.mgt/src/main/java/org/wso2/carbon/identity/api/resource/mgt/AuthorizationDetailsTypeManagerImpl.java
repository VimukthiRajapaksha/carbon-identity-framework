/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
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

package org.wso2.carbon.identity.api.resource.mgt;

import org.apache.commons.collections.CollectionUtils;
import org.wso2.carbon.identity.api.resource.mgt.dao.AuthorizationDetailsTypeMgtDAO;
import org.wso2.carbon.identity.api.resource.mgt.dao.impl.AuthorizationDetailsTypeMgtDAOImpl;
import org.wso2.carbon.identity.api.resource.mgt.dao.impl.CacheBackedAuthorizationDetailsTypeMgtDAOImpl;
import org.wso2.carbon.identity.application.common.model.AuthorizationDetailsType;

import java.util.Collections;
import java.util.List;

import static org.wso2.carbon.identity.api.resource.mgt.util.FilterQueriesUtil.getExpressionNodes;
import static org.wso2.carbon.identity.core.util.IdentityTenantUtil.getTenantId;

/**
 * Implementation of the {@link AuthorizationDetailsTypeManager} interface that provides
 * management functionalities for authorization detail types associated with APIs.
 */
public class AuthorizationDetailsTypeManagerImpl implements AuthorizationDetailsTypeManager {

    private final AuthorizationDetailsTypeMgtDAO authorizationDetailsTypeMgtDAO;

    public AuthorizationDetailsTypeManagerImpl() {

        this(new CacheBackedAuthorizationDetailsTypeMgtDAOImpl(new AuthorizationDetailsTypeMgtDAOImpl()));
    }

    public AuthorizationDetailsTypeManagerImpl(AuthorizationDetailsTypeMgtDAO authorizationDetailsTypeMgtDAO) {

        this.authorizationDetailsTypeMgtDAO = authorizationDetailsTypeMgtDAO;
    }

    @Override
    public List<AuthorizationDetailsType> addAuthorizationDetailsTypes(
            String apiId, List<AuthorizationDetailsType> authorizationDetailsTypes, String tenantDomain)
            throws APIResourceMgtException {

        return this.authorizationDetailsTypeMgtDAO.addAuthorizationDetailsTypes(apiId, authorizationDetailsTypes,
                getTenantId(tenantDomain));
    }

    @Override
    public void deleteAuthorizationDetailsTypeByApiIdAndTypeId(String apiId, String typeId, String tenantDomain)
            throws APIResourceMgtException {

        this.authorizationDetailsTypeMgtDAO
                .deleteAuthorizationDetailsTypeByApiIdAndTypeId(apiId, typeId, getTenantId(tenantDomain));
    }

    @Override
    public void deleteAuthorizationDetailsTypesByApiId(String apiId, String tenantDomain)
            throws APIResourceMgtException {

        this.authorizationDetailsTypeMgtDAO.deleteAuthorizationDetailsTypesByApiId(apiId, getTenantId(tenantDomain));
    }

    @Override
    public AuthorizationDetailsType getAuthorizationDetailsTypeByApiIdAndTypeId(
            String apiId, String typeId, String tenantDomain) throws APIResourceMgtException {

        return this.authorizationDetailsTypeMgtDAO
                .getAuthorizationDetailsTypeByApiIdAndTypeId(apiId, typeId, getTenantId(tenantDomain));
    }

    @Override
    public List<AuthorizationDetailsType> getAuthorizationDetailsTypes(String filter, String tenantDomain)
            throws APIResourceMgtException {

        return this.authorizationDetailsTypeMgtDAO
                .getAuthorizationDetailsTypes(getExpressionNodes(filter, null, null), getTenantId(tenantDomain));
    }

    @Override
    public List<AuthorizationDetailsType> getAuthorizationDetailsTypesByApiId(String apiId, String tenantDomain)
            throws APIResourceMgtException {

        return this.authorizationDetailsTypeMgtDAO
                .getAuthorizationDetailsTypesByApiId(apiId, getTenantId(tenantDomain));
    }

    @Override
    public boolean isAuthorizationDetailsTypeExists(String filter, String tenantDomain) throws APIResourceMgtException {

        return CollectionUtils.isNotEmpty(this.getAuthorizationDetailsTypes(filter, tenantDomain));
    }

    @Override
    public boolean isAuthorizationDetailsTypeExists(String apiId, String type, String tenantDomain)
            throws APIResourceMgtException {

        return this.authorizationDetailsTypeMgtDAO
                .isAuthorizationDetailsTypeExists(apiId, type, getTenantId(tenantDomain));
    }

    @Override
    public void replaceAuthorizationDetailsTypes(String apiId, List<String> removedAuthorizationDetailsTypes,
                                                 List<AuthorizationDetailsType> addedAuthorizationDetailsTypes,
                                                 String tenantDomain) throws APIResourceMgtException {

        if (CollectionUtils.isNotEmpty(removedAuthorizationDetailsTypes)) {
            for (String removedType : removedAuthorizationDetailsTypes) {
                this.authorizationDetailsTypeMgtDAO
                        .deleteAuthorizationDetailsTypeByApiIdAndType(apiId, removedType, getTenantId(tenantDomain));
            }
        }

        if (CollectionUtils.isNotEmpty(addedAuthorizationDetailsTypes)) {
            this.authorizationDetailsTypeMgtDAO
                    .addAuthorizationDetailsTypes(apiId, addedAuthorizationDetailsTypes, getTenantId(tenantDomain));
        }
    }

    @Override
    public void updateAuthorizationDetailsType(String apiId, AuthorizationDetailsType authorizationDetailsType,
                                               String tenantDomain) throws APIResourceMgtException {

        this.authorizationDetailsTypeMgtDAO.updateAuthorizationDetailsTypes(apiId,
                Collections.singletonList(authorizationDetailsType), getTenantId(tenantDomain));
    }
}