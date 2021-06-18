<template>
    <a-layout id="components-layout-demo-side" style="min-height: 100vh">
        <PageSider/>
        <a-layout>
            <PageHeader/>
            <span style="margin-left: 32px; margin-right: 32px; margin-top: 32px">
                <a-form>
                    <a-form-item>
                        <a-input placeholder="Search..." v-model="search"/>
                    </a-form-item>
                </a-form>
                <a-table :columns="columns" :data-source="tableData">
                </a-table>
            </span>
        </a-layout>
    </a-layout>
</template>

<script>
    import 'ant-design-vue/dist/antd.css';
    import PageSider from "../components/PageSider";
    import PageHeader from "../components/PageHeader"
    import {axiosPost} from "../util/Ajax";

    const columns = [
        {
            title: 'ID',
            dataIndex: 'tradeId',
        },
        {
            title: 'BROKER',
            dataIndex: 'broker',
        },
        {
            title: 'PRODUCT',
            dataIndex: 'product',
        },
        {
            title: 'PRICE',
            dataIndex: 'price',
        },
        {
            title: 'QUANTITY',
            dataIndex: 'quantity',
        },
        {
            title: 'SELL_NAME',
            dataIndex: 'sellName',
        },
        {
            title: 'SELL_COMPANY',
            dataIndex: 'sellCompany',
        },
        {
            title: 'BUY_NAME',
            dataIndex: 'buyName',
        },
        {
            title: 'BUY_COMPANY',
            dataIndex: 'buyCompany',
        },
        {
            title: 'INIT_SIDE',
            dataIndex: 'initSide',
        }
    ];

    export default {
        components: {
            'PageSider': PageSider,
            'PageHeader': PageHeader,
        },
        name: "BlotterView",
        computed: {
            tableData() {
                const search = this.search.toLowerCase();
                if (search) {
                    return this.blotter.filter(data => {
                        return Object.keys(data).some(key => {
                            return String(data[key]).toLowerCase().indexOf(search) > -1
                        })
                    })
                }
                return this.blotter;
            }
        },
        data() {
            return {
                columns,
                blotter: [],
                search: '',
            }
        },
        mounted() {
            let self = this;
            function callback(response) {
                self.blotter = response;
            }
            axiosPost(30551, '/process/transaction/findAll', {}, callback);
        },
    }
</script>

<style scoped>

</style>
