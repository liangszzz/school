<template>
    <div>
        {{msg}}
    </div>
</template>

<script>
    import {Component, Prop, Vue} from 'vue-property-decorator/lib/vue-property-decorator';

    export default class Login extends Vue{
        msg="abc";
    }
</script>

<style scoped>

</style>