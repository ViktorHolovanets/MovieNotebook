<template>
    <div class="d-flex m-2">
        <div class="checkbox-wrapper-26">
            <input type="checkbox" :id="checkboxId" :checked="internalValue" @change="updateInternalValue">
            <label :for="checkboxId">
                <div class="tick_mark"></div>
            </label>
        </div>
        <div class="text-uppercase font-weight-bold">{{label}}</div>
    </div>
</template>

<script>
export default {
    name: "checkboxComponent",
    props: {
        isChecked: {
            type: Boolean,
            required: true
        },
        label:{
            type:String,
            required: false
        }
    },
    data() {
        return {
            internalValue: this.isChecked,
            checkboxId: this.generateUniqueId()
        }
    },
    methods: {
        updateInternalValue(event) {
            this.internalValue = event.target.checked;
            this.$emit("update:isChecked", this.internalValue);
        },
        generateUniqueId() {
            // Генерація унікального ідентифікатора
            return `checkbox-${Math.random().toString(36).substr(2, 9)}`;
        }
    }
}
</script>


<style scoped>
.checkbox-wrapper-26 * {
    -webkit-tap-highlight-color: transparent;
    outline: none;
}

.checkbox-wrapper-26 input[type="checkbox"] {
    display: none;
}

.checkbox-wrapper-26 label {
    --size: 20px;
    position: relative;
    display: block;
    width: var(--size);
    height: var(--size);
    margin: 0 auto;
    background-color: #1449f7;
    border-radius: 50%;
    cursor: pointer;
    transition: 0.2s ease transform, 0.2s ease background-color,
    0.2s ease box-shadow;
    overflow: hidden;
    z-index: 1;
}

.checkbox-wrapper-26 label:before {
    content: "";
    position: absolute;
    top: 50%;
    right: 0;
    left: 0;
    width: calc(var(--size) * .7);
    height: calc(var(--size) * .7);
    margin: 0 auto;
    background-color: #fff;
    transform: translateY(-50%);
    border-radius: 50%;
    transition: 0.2s ease width, 0.2s ease height;
}

.checkbox-wrapper-26 label:hover:before {
    width: calc(var(--size) * .55);
    height: calc(var(--size) * .55);
}

.checkbox-wrapper-26 label:active {
    transform: scale(0.9);
}

.checkbox-wrapper-26 .tick_mark {
    position: absolute;
    top: -1px;
    right: 0;
    left: calc(var(--size) * -.05);
    width: calc(var(--size) * .6);
    height: calc(var(--size) * .6);
    margin: 0 auto;
    margin-left: calc(var(--size) * .14);
    transform: rotateZ(-40deg);
}

.checkbox-wrapper-26 .tick_mark:before,
.checkbox-wrapper-26 .tick_mark:after {
    content: "";
    position: absolute;
    background-color: #fff;
    border-radius: 2px;
    opacity: 0;
    transition: 0.2s ease transform, 0.2s ease opacity;
}

.checkbox-wrapper-26 .tick_mark:before {
    left: 0;
    bottom: 0;
    width: calc(var(--size) * .1);
    height: calc(var(--size) * .3);
    box-shadow: -2px 0 5px rgba(220, 217, 217, 0.23);
    transform: translateY(calc(var(--size) * -.68));
}

.checkbox-wrapper-26 .tick_mark:after {
    left: 0;
    bottom: 0;
    width: 100%;
    height: calc(var(--size) * .1);
    box-shadow: 0 3px 5px rgba(245, 242, 242, 0.23);
    transform: translateX(calc(var(--size) * .78));
}

.checkbox-wrapper-26 input[type="checkbox"]:checked + label {
    background-color: #07d410;
}

.checkbox-wrapper-26 input[type="checkbox"]:checked + label:before {
    width: 0;
    height: 0;
}

.checkbox-wrapper-26 input[type="checkbox"]:checked + label .tick_mark:before,
.checkbox-wrapper-26 input[type="checkbox"]:checked + label .tick_mark:after {
    transform: translate(0);
    opacity: 1;
}
</style>