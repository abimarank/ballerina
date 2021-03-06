// Copyright (c) 2018 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.


documentation {
    The Caller actions for SQL databases.
}
public type CallerActions object {

    documentation {
        The call action implementation for SQL connector to invoke stored procedures/functions.

        P{{sqlQuery}} SQL statement to execute
        P{{recordType}} Array of record types of the returned tables if there is any
        R{{}} A `table[]` if there are tables returned by the call action and else nil,
                `error` will be returned if there is any error
    }
    public native function call(@sensitive string sqlQuery, typedesc[]? recordType, Param... parameters)
        returns @tainted table[]|()|error;

    documentation {
        The select action implementation for SQL connector to select data from tables.

        P{{sqlQuery}} SQL query to execute
        P{{recordType}} Type of the returned table
        P{{loadToMemory}} Indicates whether to load the retrieved data to memory or not
        R{{}} A `table` returned by the sql query statement else `error` will be returned if there is any error
    }
    public native function select(@sensitive string sqlQuery, typedesc? recordType, boolean loadToMemory = false,
                                  Param... parameters) returns @tainted table|error;

    documentation {
        The update action implementation for SQL connector to update data and schema of the database.

        P{{sqlQuery}} SQL statement to execute
        R{{}} `int` number of rows updated by the statement and else `error` will be returned if there is any error
    }
    public native function update(@sensitive string sqlQuery, Param... parameters) returns int|error;

    documentation {
        The batchUpdate action implementation for SQL connector to batch data insert.

        P{{sqlQuery}} SQL statement to execute
        R{{}} An `int[]` array of updated row count by each of statements in batch and
                else `error` will be returned if there is any error
    }
    public native function batchUpdate(@sensitive string sqlQuery, Param[]... parameters) returns int[]|error;

    documentation {
        The updateWithGeneratedKeys action implementation for SQL connector which returns the auto
        generated keys during the update action.

        P{{sqlQuery}} SQL statement to execute
        P{{keyColumns}} Names of auto generated columns for which the auto generated key values are returned
        R{{}} A `Tuple` will be returned and would represent updated row count during the query exectuion,
            aray of auto generated key values during the query execution, in order.
            Else `error` will be returned if there is any error.
    }
    public native function updateWithGeneratedKeys(@sensitive string sqlQuery, string[]? keyColumns,
                                                   Param... parameters) returns (int, string[])|error;

    documentation {
        The getProxyTable action implementation for SQL connector which acts as a proxy for a database
        table that allows performing select/update operations over the actual database table.

        P{{tableName}} The name of the table to be retrieved
        P{{recordType}} The record type of the returned table
        R{{}} A `table` returned by the sql query statement else `error` will be returned if there is any error

    }
    public native function getProxyTable(@sensitive string tableName, typedesc recordType) returns @tainted table|error;
};

documentation {
        An internal function used by clients to shutdown the connection pool.

        P{{callerActions}} The CallerActions object which represents the connection pool.
}
public native function close(CallerActions callerActions);
