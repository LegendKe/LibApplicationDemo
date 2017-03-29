package com.ptja.android.mms.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhenghou on 2016/6/8.
 */
public class EquipmentBean implements Serializable{


    /**
     * equipment_id : 14
     * equipment_name : 123456
     * model : 123456
     * standard : 123456
     * code : S9YQ14+y2WNFCFb0PYgsklThIDyYtJcoxYMXWZBZ5uvj77UDknmhlwzKRr6K/tWL
     * unit : 双
     * total_number : 123456
     * current_number : 123456
     * equipment_type_id : 1
     * depot_id : 1
     * created_at : 2016-06-12 18:45:09
     * status : 1
     * pro_date : 2016-06-12
     * shelf_life : 123456
     * life_span : 0
     * use_count : 0
     * maintenance_count : 0
     * gallery_id : 21
     * equipment_type : {"equipment_type_id":"1","equipment_type_name":"办公用品"}
     * depot : {"depot_id":"1","depot_name":"第一仓库"}
     * gallery : {"gallery_id":"21","created_at":"2016-06-12 18:45:09","creator_id":"1","images":[{"image_id":"41","url":"http://121.42.46.157:8081/Uploads/Download/2016-06-12/575d3d3565db6.jpg","gallery_id":"21"},{"image_id":"42","url":"http://121.42.46.157:8081/Uploads/Download/2016-06-12/575d3d356c8a1.jpg","gallery_id":"21"}]}
     */

    private String equipment_id;
    private String equipment_name;
    private String model;
    private String standard;
    private String code;
    private String unit;
    private String total_number;
    private String current_number;
    private String equipment_type_id;
    private String depot_id;
    private String created_at;
    private String status;
    private String pro_date;
    private String shelf_life;
    private String life_span;
    private String use_count;
    private String maintenance_count;
    private String gallery_id;
    /**
     * equipment_type_id : 1
     * equipment_type_name : 办公用品
     */

    private EquipmentTypeBean equipment_type;
    /**
     * depot_id : 1
     * depot_name : 第一仓库
     */

    private DepotBean depot;
    /**
     * gallery_id : 21
     * created_at : 2016-06-12 18:45:09
     * creator_id : 1
     * images : [{"image_id":"41","url":"http://121.42.46.157:8081/Uploads/Download/2016-06-12/575d3d3565db6.jpg","gallery_id":"21"},{"image_id":"42","url":"http://121.42.46.157:8081/Uploads/Download/2016-06-12/575d3d356c8a1.jpg","gallery_id":"21"}]
     */

    private GalleryBean gallery;

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTotal_number() {
        return total_number;
    }

    public void setTotal_number(String total_number) {
        this.total_number = total_number;
    }

    public String getCurrent_number() {
        return current_number;
    }

    public void setCurrent_number(String current_number) {
        this.current_number = current_number;
    }

    public String getEquipment_type_id() {
        return equipment_type_id;
    }

    public void setEquipment_type_id(String equipment_type_id) {
        this.equipment_type_id = equipment_type_id;
    }

    public String getDepot_id() {
        return depot_id;
    }

    public void setDepot_id(String depot_id) {
        this.depot_id = depot_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPro_date() {
        return pro_date;
    }

    public void setPro_date(String pro_date) {
        this.pro_date = pro_date;
    }

    public String getShelf_life() {
        return shelf_life;
    }

    public void setShelf_life(String shelf_life) {
        this.shelf_life = shelf_life;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getUse_count() {
        return use_count;
    }

    public void setUse_count(String use_count) {
        this.use_count = use_count;
    }

    public String getMaintenance_count() {
        return maintenance_count;
    }

    public void setMaintenance_count(String maintenance_count) {
        this.maintenance_count = maintenance_count;
    }

    public String getGallery_id() {
        return gallery_id;
    }

    public void setGallery_id(String gallery_id) {
        this.gallery_id = gallery_id;
    }

    public EquipmentTypeBean getEquipment_type() {
        return equipment_type;
    }

    public void setEquipment_type(EquipmentTypeBean equipment_type) {
        this.equipment_type = equipment_type;
    }

    public DepotBean getDepot() {
        return depot;
    }

    public void setDepot(DepotBean depot) {
        this.depot = depot;
    }

    public GalleryBean getGallery() {
        return gallery;
    }

    public void setGallery(GalleryBean gallery) {
        this.gallery = gallery;
    }

    public static class EquipmentTypeBean implements Serializable{
        private String equipment_type_id;
        private String equipment_type_name;

        public String getEquipment_type_id() {
            return equipment_type_id;
        }

        public void setEquipment_type_id(String equipment_type_id) {
            this.equipment_type_id = equipment_type_id;
        }

        public String getEquipment_type_name() {
            return equipment_type_name;
        }

        public void setEquipment_type_name(String equipment_type_name) {
            this.equipment_type_name = equipment_type_name;
        }
    }

    public static class DepotBean implements Serializable {
        private String depot_id;
        private String depot_name;

        public String getDepot_id() {
            return depot_id;
        }

        public void setDepot_id(String depot_id) {
            this.depot_id = depot_id;
        }

        public String getDepot_name() {
            return depot_name;
        }

        public void setDepot_name(String depot_name) {
            this.depot_name = depot_name;
        }
    }

    public static class GalleryBean implements Serializable{
        private String gallery_id;
        private String created_at;
        private String creator_id;
        /**
         * image_id : 41
         * url : http://121.42.46.157:8081/Uploads/Download/2016-06-12/575d3d3565db6.jpg
         * gallery_id : 21
         */

        private List<ImagesBean> images;

        public String getGallery_id() {
            return gallery_id;
        }

        public void setGallery_id(String gallery_id) {
            this.gallery_id = gallery_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getCreator_id() {
            return creator_id;
        }

        public void setCreator_id(String creator_id) {
            this.creator_id = creator_id;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean implements Serializable{
            private String image_id;
            private String url;
            private String gallery_id;

            public String getImage_id() {
                return image_id;
            }

            public void setImage_id(String image_id) {
                this.image_id = image_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getGallery_id() {
                return gallery_id;
            }

            public void setGallery_id(String gallery_id) {
                this.gallery_id = gallery_id;
            }
        }
    }
}
