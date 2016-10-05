# -PANDORA-LECTURE-13-User-Permission-NAMMU-like-interface-with-callback
Simple Permission Requesting Interface like NAMMU
For [PANDORA] Coding Blocks Assignment


EasyPermission.askSimplePermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, 10, new EasyPermission.OnRequestSimplePermission() {
                    @Override
                    public void onSimplePermissionGranted() {
                       
                    }

                    @Override
                    public void OnSimplePermissionDenied() {
                        Toast.makeText(MainActivity.this,"NO EASY PERMISSION",Toast.LENGTH_LONG).show();
                    }
                });
