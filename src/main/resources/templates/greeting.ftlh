<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="greeting-container">
        <h1 class="greeting-title">
            <#if user??>
                Welcome ${name} ${surname}!
            <#else>
                Please log in
            </#if>
        </h1>
        <h5 class="greeting-text">This is simple spring boot mvc App</h5>
    </div>
    <#if user??>
        <script>
            setTimeout(function () {
                window.location = "/main";
            }, 1000);
        </script>
    <#else>
        <script>
            setTimeout(function () {
                window.location = "/login";
            }, 1000);
        </script>
    </#if>
</@c.page>